package com.nit.ssm.service.impl;

import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.entity.SortEntity;
import com.nit.ssm.mapper.SortMapper;
import com.nit.ssm.service.SortService;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

 @Service
public class SortServiceImpl implements SortService {

    @Autowired(required = false)
    private SortMapper sortMapper;
//
//    @Override
//    public TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception {
//        Integer count = sortMapper.count4Table(tableReqDTO.getQueryText());
//        List<SortDTO> listSortDTOs = sortMapper.list4Table(tableReqDTO.getStart(),
//                tableReqDTO.getPageSize(), tableReqDTO.getQueryText());
//        return new TableRspDTO(count, listSortDTOs);
//    }

     @Override
     public TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception {
         return null;
     }

     @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(SortDTO sortDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        SortEntity sortEntity = mapperFactory.getMapperFacade().map(sortDTO, SortEntity.class);
        return sortMapper.add(sortEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer edit(SortDTO sortDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        SortEntity sortEntity = mapperFactory.getMapperFacade().map(sortDTO, SortEntity.class);
        return sortMapper.edit(sortEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(Integer sortId) throws Exception {
        return sortMapper.remove(sortId);
    }
}


