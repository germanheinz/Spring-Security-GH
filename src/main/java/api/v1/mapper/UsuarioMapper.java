package api.v1.mapper;


import api.v1.model.UsuarioDTO;
import com.springbootangular.api.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

}
