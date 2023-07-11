package com.example.webbansim.service.Impl;

import com.example.webbansim.entity.TaiKhoan;
import com.example.webbansim.model.dto.TaiKhoan.TaiKhoanDTO;
import com.example.webbansim.model.dto.TaiKhoan.TaiKhoanMapper;
import com.example.webbansim.model.request.TaiKhoan.FindTaiKhoanReq;
import com.example.webbansim.repository.TaiKhoanRepository;
import com.example.webbansim.service.ITaiKhoanService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaiKhoanServiceImpl implements ITaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public List<TaiKhoanDTO> getListTaiKhoan(Integer id) {
        List<TaiKhoan>listTaiKhoan = taiKhoanRepository.findByIdRole(id);
        List<TaiKhoanDTO>listDTO = new ArrayList<>();
        for(TaiKhoan tmp : listTaiKhoan){
            listDTO.add(TaiKhoanMapper.toTaiKhoanDTO(tmp));
        }
        return listDTO;
    }

    @Override
    public TaiKhoanDTO findTaiKhoanByEmail(String email) {
        return null;
    }

    @Override
    public TaiKhoanDTO signup(TaiKhoanDTO taiKhoanDTO) {
        taiKhoanDTO.setPass(BCrypt.hashpw(taiKhoanDTO.getPass(), BCrypt.gensalt(12)));
        taiKhoanRepository.save(TaiKhoanMapper.toTaiKhoan(taiKhoanDTO));
        return taiKhoanDTO;
    }

    @Override
    public TaiKhoan update(Integer userID, TaiKhoanDTO taiKhoanDTO) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(userID);
        if(taiKhoan.isPresent()){
            TaiKhoan updateTaiKhoan = new TaiKhoan()
                    .setId(Long.valueOf(userID))
                    .setUserName(taiKhoanDTO.getUsername())
                    .setPass(taiKhoanDTO.getPass())
                    .setEmail(taiKhoanDTO.getEmail())
                    .setPhone(taiKhoanDTO.getPhone())     ;
            return taiKhoanRepository.save(updateTaiKhoan);
        }
        return null;
    }

    @Override
    public List<TaiKhoanDTO> findUser(FindTaiKhoanReq findTaiKhoanReq) {
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findByEmail(findTaiKhoanReq.getEmail());
        List<TaiKhoanDTO>listDTO = new ArrayList<>();
        for(TaiKhoan tmp : taiKhoanList){
            listDTO.add(TaiKhoanMapper.toTaiKhoanDTO(tmp));
        }
        return listDTO;
    }
}
