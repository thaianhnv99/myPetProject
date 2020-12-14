package ttn.com.project.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "lever")
    private String lever;
    @Column(name = "hoten")
    private String hoten;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "email")
    private String email;
    @Column(name = "cccd")
    private String cccd;
    @Column(name = "ngaysinh")
    private String ngaysinh;
    @Column(name = "diachi")
    private String diachi;
    @Column(name = "chucvu")
    private String chucvu;
    @Column(name = "quyentruycap")
    private String quyentruycap;
}
