package it.dinfo.stlab.mappers;

import it.dinfo.stlab.model.user.UserAccount;
import it.dinfo.stlab.dto.UserAccountDTO;
import it.dinfo.stlab.model.user.UserRole;

public class UserAccountMapper {

    public UserAccountMapper(){}

    public UserAccountDTO convert(UserAccount u){
        if(u == null)
            return null;

        UserAccountDTO dto = new UserAccountDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setSurname(u.getSurname());
        dto.setEmail(u.getEmail());
        dto.setUserRole(u.getUserRole().name());

        return dto;
    }

    public UserAccount transfer(UserAccountDTO dto, UserAccount u){
        if(dto == null)
            return null;
        if(u == null)
            return null;

        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setEmail(dto.getEmail());
        u.setUserRole(UserRole.valueOf(dto.getUserRole()));
        u.assignPassword(dto.getPassword());
        return u;
    }
}
