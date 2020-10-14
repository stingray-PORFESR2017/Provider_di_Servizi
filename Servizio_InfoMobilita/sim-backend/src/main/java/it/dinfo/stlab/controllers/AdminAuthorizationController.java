package it.dinfo.stlab.controllers;

import it.dinfo.stlab.dao.AdminAuthorizationDao;
import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.dto.AdminAuthorizationDTO;
import it.dinfo.stlab.mappers.AdminAuthorizationMapper;
import it.dinfo.stlab.model.AdminAuthorization;
import it.dinfo.stlab.model.user.UserAccount;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminAuthorizationController {

    @Inject private AdminAuthorizationDao adminAuthDao;
    @Inject private AdminAuthorizationMapper adminAuthMapper;
    @Inject private UserAccountDao userAccountDao;

    public List<AdminAuthorizationDTO> getAll(){

        List<AdminAuthorizationDTO> adminAuthorizationDTOS = new ArrayList<>();
        List<AdminAuthorization> adminAuthorizations = adminAuthDao.findAll();

        for (AdminAuthorization authorization : adminAuthorizations) {
            AdminAuthorizationDTO dto = adminAuthMapper.convert(authorization);
            adminAuthorizationDTOS.add(dto);
        }
        return adminAuthorizationDTOS;
    }

    public AdminAuthorizationDTO getById(String uuid){
        AdminAuthorization adminAuth = adminAuthDao.findById(uuid);
        AdminAuthorizationDTO dto = adminAuthMapper.convert(adminAuth);
        return dto;
    }

    public String create(AdminAuthorizationDTO dtoReceived){
        AdminAuthorization adminAuth = adminAuthMapper.transfer(dtoReceived,new AdminAuthorization());
        adminAuth.setId(UUID.randomUUID().toString());
        adminAuthDao.save(adminAuth);
        return adminAuth.getId();
    }

    public void update(String uuid, AdminAuthorizationDTO dtoReceived){
        AdminAuthorization byId = adminAuthDao.findById(uuid);
        adminAuthMapper.transfer(dtoReceived,byId);
        adminAuthDao.update(byId);
    }

    public void delete(String uuid){
        adminAuthDao.delete(uuid);
    }

    public List<AdminAuthorizationDTO> getAllAuthForOneUser(String userId){
        UserAccount u = userAccountDao.findById(userId);
        List<AdminAuthorization> adminAuthorizations = adminAuthDao.findAllAuthorizationForOneUser(u);
        List<AdminAuthorizationDTO> adminAuthorizationDTOS = new ArrayList<>();
        for (AdminAuthorization authorization : adminAuthorizations) {
            AdminAuthorizationDTO dto = adminAuthMapper.convert(authorization);
            adminAuthorizationDTOS.add(dto);
        }
        return adminAuthorizationDTOS;
    }
}
