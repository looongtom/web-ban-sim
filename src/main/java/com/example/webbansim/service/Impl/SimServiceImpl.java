package com.example.webbansim.service.Impl;

import com.example.webbansim.entity.Sim;
import com.example.webbansim.model.dto.Sim.SimDTO;
import com.example.webbansim.model.dto.Sim.SimMapper;
import com.example.webbansim.model.request.Sim.FindSimReq;
import com.example.webbansim.repository.SimRepository;
import com.example.webbansim.service.ISimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class SimServiceImpl implements ISimService {

    @Autowired
    private SimRepository simRepository;

    @Override
    public List<SimDTO> getAllSim() {
        List<Sim> simDTOList= simRepository.findAll();
        List<SimDTO>dtoList = new ArrayList<>();
        for(Sim tmp: simDTOList){
            dtoList.add(SimMapper.toSimDTO(tmp));
        }
        return dtoList;
    }

    @Override
    public SimDTO saveSim(SimDTO newSim) {
        simRepository.save(SimMapper.toSim(newSim));
        return  newSim;
    }

    @Override
    public SimDTO getSimById(Long id) {
        return null;
    }

    @Override
    public SimDTO updateSim(Sim oldSim) {
        return null;
    }

    @Override
    public List<SimDTO> findBySo(FindSimReq findSimReq) {
        List<Sim> simList = simRepository.findBySoContainingOrIdNmOrPrice(findSimReq.getSo(), findSimReq.getPrice(), findSimReq.getIdType());
//        List<Sim> simList = simRepository.findBySo(findSimReq.getSo());
        List<SimDTO>dtoList = new ArrayList<>();
        for(Sim tmp :simList){
            dtoList.add(SimMapper.toSimDTO(tmp));
        }
        return  dtoList;
    }

    @Override
    public SimDTO findByIdSim(Long id) {
        Sim sim = simRepository.findByIdSim(id);
        return SimMapper.toSimDTO(sim);
    }

    @Override
    public void deleteSimById(Long id) {
        simRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public Page<SimDTO> findPaginated(int pageNo, int pageSize,String keyword) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<Sim> simDTOList=null;
        if(keyword == null || keyword.equals("null")){
            keyword="";
        }
        simDTOList= simRepository.
                findBySoContaining(pageable,keyword);



//                findAll(pageable);
        Page<SimDTO> dtoPage = simDTOList.map(new Function<Sim, SimDTO>() {
            @Override
            public SimDTO apply(Sim sim) {
                return SimMapper.toSimDTO(sim);
            }
        });

        return dtoPage;
    }
}
