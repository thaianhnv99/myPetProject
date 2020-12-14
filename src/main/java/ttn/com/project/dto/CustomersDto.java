package ttn.com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDto extends BaseRequest {
    private int id;
    private String accountNumber;
    private String balance;
    private String firstname;
    private String lastname;
    private String age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
