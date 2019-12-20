package api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    private  String email;
}
