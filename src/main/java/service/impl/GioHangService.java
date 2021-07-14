/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.GioHangEntity;
import entity.KhuyenMaiEntity;
import entity.SanPhamEntity;
import entity.SanPhamKhuyenMaiEntity;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IGioHangRepository;
import repository.IKhachHangRepository;
import repository.ISanPhamRepository;
import service.IGioHangService;
import java.text.SimpleDateFormat;
import java.util.Date;
import repository.IHoaDonRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class GioHangService implements IGioHangService {

    final private int maximumDebt = -30000000;
    protected static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    final private int maGiamGia = 10;

    @Autowired
    IGioHangRepository ghRepository;

    @Autowired
    IKhachHangRepository khRepository;

    @Autowired
    ISanPhamRepository spRepository;

    @Autowired
    IHoaDonRepository hdRepository;

    @Override
    public String createCart(GioHangEntity cart) {
        if (khRepository.findOne(cart.getMakh()) == null) {
            return "Không tìm thấy khách hàng";
        } else {
            int magiohang = ghRepository.isExists(cart.getMasp(), cart.getMakh());
            SanPhamEntity sp = spRepository.findById(cart.getMasp());
            int soluong = sp.getSoLuong() - cart.getSoluong();
            if (spRepository.updateSoluong(sp.getMaSP(), soluong) == true) {
                if (magiohang < 0) {
                    cart.setTensp(sp.getTenSP());
                    if (ghRepository.create(cart) == true) {
                        return "Thêm giỏ hàng thành công";
                    } else {
                        return "Thêm giỏ hàng thất bại";
                    }
                } else {
                    if (ghRepository.incremental(magiohang, cart.getGiatong(), cart.getSoluong()) == true) {
                        return "Thêm giỏ hàng thành công";
                    } else {
                        return "Thêm giỏ hàng thất bại";
                    }
                }
            } else {
                return "Thêm giỏ hàng thất bại";
            }
        }
    }

    @Override
    public String deleteCart(int magiohang) {
        GioHangEntity gh = ghRepository.findById(magiohang);
        SanPhamEntity sp = spRepository.findById(gh.getMasp());
        int soluong = sp.getSoLuong() + gh.getSoluong();
        if (spRepository.updateSoluong(sp.getMaSP(), soluong) == true) {
            if (ghRepository.delete(magiohang) == true) {
                return "Xóa giỏ hàng thành công";
            } else {
                return "Xóa giỏ hàng thất bại";
            }
        } else {
            return "Xóa giỏ hàng thất bại";
        }
    }

    @Override
    public ArrayList<GioHangEntity> getAll(int makh) {
        return ghRepository.findAll(makh);
    }

    @Override
    public int totalPrice(int makh) {
        int total = ghRepository.totalPrice(makh);
        return total;
    }

    @Override
    public int discount(int makh) {
        ArrayList<KhuyenMaiEntity> sp_km = khuyenMai(makh);
        ArrayList<KhuyenMaiEntity> chietKhau = chietKhau(makh);

        int discount = 0;
        if (chietKhau != null) {
            for (KhuyenMaiEntity ck : chietKhau) {
                discount += ck.getGiaTienGiam();
            }
        }

        if (sp_km != null) {
            for (KhuyenMaiEntity spkm : sp_km) {
                discount += spkm.getGiaTienGiam();
            }
        }
        return discount;
    }

    @Override
    public ArrayList<KhuyenMaiEntity> khuyenMai(int makh) {
        ArrayList<Integer> masp = spRepository.getMaSanPham(makh);
        ArrayList<SanPhamKhuyenMaiEntity> spKm = new ArrayList<SanPhamKhuyenMaiEntity>();

        ArrayList<KhuyenMaiEntity> listKm = new ArrayList<KhuyenMaiEntity>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date toDate = new Date();

        if (masp != null) {
            for (int i = 0; i < masp.size(); i++) {
                SanPhamKhuyenMaiEntity sp = new SanPhamKhuyenMaiEntity();
                sp = ghRepository.getThongTinKhuyenMaiSanPham(masp.get(i));
                if (sp.getTenKhuyenMai() != null) {
                    spKm.add(sp);
                }
            }
        } else {
            spKm = null;
        }

        ArrayList<KhuyenMaiEntity> chietKhau = chietKhau(makh);

        if (spKm != null) {
            for (SanPhamKhuyenMaiEntity spkm : spKm) {
                if ((spkm.getNgayBatDauKhuyenMai().before(toDate) || spkm.getNgayBatDauKhuyenMai().equals(toDate)) && (toDate.before(spkm.getNgayKetThucKhuyenMai()) || spkm.getNgayKetThucKhuyenMai().equals(toDate))) {
                    int sl_sp = 0;
                    KhuyenMaiEntity thongTinKhuyenMai = new KhuyenMaiEntity();
                    sl_sp = ghRepository.soLuongSanPhamTrongGioHang(spkm.getMaSanPham(), makh);
                    int giaSp = 0;
                    int demKhachHangChietKhau = 0;

                    for (KhuyenMaiEntity ck : chietKhau) {
                        if (ck.getTenKhuyenMai().equals(String.valueOf(spkm.getMaSanPham())) && ck.getGiaTienGiam() > 0) {
                            giaSp = spkm.getGiaTongSanPham() - ck.getGiaTienGiam();
                            demKhachHangChietKhau = 1;
                        }
                    }

                    if (demKhachHangChietKhau == 0) {
                        giaSp = spkm.getGiaTongSanPham();
                    }

                    int giaSpGiamGia = (int) (giaSp * ((float) spkm.getMaGiamGia() / 100));

                    if (giaSpGiamGia > spkm.getToiDaKhuyenMai() && spkm.getToiDaKhuyenMai() != 0) {
                        giaSpGiamGia = spkm.getToiDaKhuyenMai();
                    }
                    String tenKm = spkm.getTenKhuyenMai() + " ( mã sản phẩm " + spkm.getMaSanPham() + " giảm tối đa " + spkm.getToiDaKhuyenMai() + "đ )";
                    thongTinKhuyenMai.setGiaTienGiam(giaSpGiamGia);
                    thongTinKhuyenMai.setPhanTramGiamGia(spkm.getMaGiamGia());
                    thongTinKhuyenMai.setTenKhuyenMai(tenKm);
                    listKm.add(thongTinKhuyenMai);
                }
            }
        } else {
            listKm = null;
        }
        return listKm;
    }

    @Override
    public ArrayList<KhuyenMaiEntity> chietKhau(int makh) {
        ArrayList<KhuyenMaiEntity> chietKhau = new ArrayList<KhuyenMaiEntity>();
        ArrayList<Integer> masp = spRepository.getMaSanPham(makh);
        if (masp != null) {
            for (int sp : masp) {
                KhuyenMaiEntity thongTinChietKhau = new KhuyenMaiEntity();
                int giaSp = spRepository.getGiaSanPham(sp, makh);
                int soLg = ghRepository.soLuongSanPhamTrongGioHang(sp, makh);

                if (soLg >= 10) {
                    String ten = String.valueOf(sp);
                    int giaGiam = (int) (giaSp * (float) 0.05);
                    int phanTramGiamGia = 5;

                    thongTinChietKhau.setGiaTienGiam(giaGiam);
                    thongTinChietKhau.setPhanTramGiamGia(phanTramGiamGia);
                    thongTinChietKhau.setTenKhuyenMai(ten);
                    chietKhau.add(thongTinChietKhau);
                } else {
                    String ten = String.valueOf(sp);
                    thongTinChietKhau.setGiaTienGiam(0);
                    thongTinChietKhau.setPhanTramGiamGia(0);
                    thongTinChietKhau.setTenKhuyenMai(ten);
                    chietKhau.add(thongTinChietKhau);
                }
            }
        } else {
            return null;
        }
        return chietKhau;
    }

    @Override
    public ArrayList<KhuyenMaiEntity> tongKhuyenMai(int makh) {
        ArrayList<KhuyenMaiEntity> khuyenMai = khuyenMai(makh);
        ArrayList<KhuyenMaiEntity> tongKhuyenMai = new ArrayList<KhuyenMaiEntity>();
        if (khuyenMai != null) {
            tongKhuyenMai = khuyenMai;
        }

        int totalPrice = totalPrice(makh);
        int discount = discount(makh);

        int price = totalPrice - discount;

        int kiemTraKhachHangThanThiet = khRepository.kiemTraKhachHangThanThiet(makh);

        if (kiemTraKhachHangThanThiet > 0 && price > 0) {
            KhuyenMaiEntity thongTinKhuyenMai = new KhuyenMaiEntity();
            int giaSpGiamGia = (int) (price * 0.1);
//            int maGiamGia = 10;
            thongTinKhuyenMai.setGiaTienGiam(giaSpGiamGia);
            thongTinKhuyenMai.setPhanTramGiamGia(maGiamGia);
            thongTinKhuyenMai.setTenKhuyenMai("Khách hàng thân thiết");
            tongKhuyenMai.add(thongTinKhuyenMai);
        }

        if (tongKhuyenMai != null) {
            return tongKhuyenMai;
        } else {
            return null;
        }

    }

    @Override
    public int tienGiamCuaKhachHangThanThiet(int makh) {
        int priceResult = 0;
        int totalPrice = totalPrice(makh);
        int discount = discount(makh);

        int price = totalPrice - discount;

        int kiemTraKhachHangThanThiet = khRepository.kiemTraKhachHangThanThiet(makh);
        if (kiemTraKhachHangThanThiet > 0) {
            priceResult = (int) (price * 0.1);
        }
        return priceResult;
    }

    @Override
    public String checkoutCart(int makh) {
        int lastHoaDonId = hdRepository.getMaHoaDonCuoi();
        int hoaDonId = lastHoaDonId + 1;
        java.util.Date toDate = new Date();
        int totalPrice = totalPrice(makh);
        int discount = discount(makh);

        int tienGiamKhachHangThanThiet = tienGiamCuaKhachHangThanThiet(makh);
        int tongTien = totalPrice - discount - tienGiamKhachHangThanThiet;

        boolean addDonHangResult = hdRepository.addDonHang(hoaDonId, makh, toDate, tongTien, tienGiamKhachHangThanThiet);

        ArrayList<Integer> masp = spRepository.getMaSanPham(makh);
        for (int sp : masp) {
            int giaSp = spRepository.getGiaSanPham(sp, makh);
            int soLg = ghRepository.soLuongSanPhamTrongGioHang(sp, makh);
            int tongTienBanDau = giaSp;
            int chietKhau = 0;
            int tienGiamChietKhau = 0;
            int tienGiamSauChietKhau = 0;
            int khuyenMaiKhac = 0;
            int tienGiamSauKhuyenMai = 0;
            int tongTienSauCung = 0;
            if (soLg >= 10) {
                chietKhau = 5;
                tienGiamChietKhau = (int) (giaSp * (float) 0.05);
            }
            tienGiamSauChietKhau = giaSp - tienGiamChietKhau;
            SanPhamKhuyenMaiEntity ttkm = new SanPhamKhuyenMaiEntity();
            ttkm = ghRepository.getThongTinKhuyenMaiSanPham(sp);
            if (ttkm.getTenKhuyenMai() != null && (ttkm.getNgayBatDauKhuyenMai().before(toDate) || ttkm.getNgayBatDauKhuyenMai().equals(toDate)) && (toDate.before(ttkm.getNgayKetThucKhuyenMai()) || ttkm.getNgayKetThucKhuyenMai().equals(toDate))) {
                khuyenMaiKhac = ttkm.getMaGiamGia();
                tienGiamSauKhuyenMai = (int) (tienGiamSauChietKhau * ((float) ttkm.getMaGiamGia() / 100));

                if (tienGiamSauKhuyenMai > ttkm.getToiDaKhuyenMai() && ttkm.getToiDaKhuyenMai() != 0) {
                    tienGiamSauKhuyenMai = ttkm.getToiDaKhuyenMai();
                }
            }
            tongTienSauCung = tienGiamSauChietKhau - tienGiamSauKhuyenMai;
            int maChiTietHoaDonCuoi = hdRepository.getMaChiTietHoaDonCuoi() + 1;
            boolean addChiTietHoaDonResult = hdRepository.addChiTietHoaDon(maChiTietHoaDonCuoi, hoaDonId, sp, soLg, giaSp, tongTienBanDau, chietKhau, khuyenMaiKhac, tongTienSauCung);
        }

        int soDuTaiKhoanKhachHang = khRepository.getSoDuTaiKhoanKhachHang(makh);
        int soDuSauCapNhat = soDuTaiKhoanKhachHang - tongTien;
        String formatPrìce = FORMATTER.format(maximumDebt);
        if (soDuTaiKhoanKhachHang < maximumDebt) {
            return "Thanh toán thất bại, bạn đã nợ vượt quá " + formatPrìce + ", vui lòng nạp thêm tiền!";
        } else if (soDuSauCapNhat < maximumDebt) {
            return "Thanh toán thất bại, Đơn hàng này đã làm vượt quá số nợ " + formatPrìce + ", vui lòng nạp thêm tiền!";
        } else {
            if (soDuSauCapNhat < 0) {
                int khachHangThuong = 0;
                khRepository.updateVip(makh, khachHangThuong);
            }

            boolean resultCapNhatTaiKhoanKhachHang = khRepository.capNhatSoDuTaiKhoan(makh, soDuSauCapNhat);
            if (addDonHangResult && resultCapNhatTaiKhoanKhachHang) {
                boolean deleteCartResult = ghRepository.xoaGioHang(makh);
                if (deleteCartResult) {
                    return "Bạn đã thanh toán đơn hàng thành công";
                } else {
                    return "";
                }
            } else {
                return "Bạn đã thanh toán đơn hàng thất bại";
            }
        }

    }

}
