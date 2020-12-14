package ttn.com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    private String id;
    private String username;
    private String password;
    private String lever;
    private String hoten;
    private String sdt;
    private String email;
    private String cccd;
    private String ngaysinh;
    private String diachi;
    private String chucvu;
    private String quyentruycap;
}
