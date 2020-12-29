package com.nit.ssm.service.impl;

import com.nit.ssm.dto.*;
import com.nit.ssm.entity.GarbageEntity;
import com.nit.ssm.mapper.GarbageMapper;
import com.nit.ssm.service.GarbageService;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
public class GarbageServiceImpl implements GarbageService {
    @Resource
    private GarbageMapper garbageMapper;

    @Override
    public OpResultDTO listAllGarbage() throws Exception{
        OpResultDTO op = new OpResultDTO();
        op.setResult(garbageMapper.listAllGarbage());
        return op;
    }

    @Override
    public TableRspDTO list4Table(TableReqDTO req) throws Exception {
        String garbageFlag = req.parseQueryParam("garbageFlag");
        String garbageName = req.parseQueryParam("garbageName");
        String sortId = req.parseQueryParam("sortId");
        Long count = garbageMapper.count4Table(
                garbageName,
                sortId);
        PagingDTO pagingDTO = new PagingDTO(
                req.getPageNo(),
                req.getPageSize(),
                count,
                garbageMapper.list4Table(
                        garbageName,
                        sortId,
                        req.getStart(),
                        req.getPageSize(),
                        req.getSortField(),
                        req.getSortOrder()));
        return new TableRspDTO(pagingDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO addList(List<GarbageDTO> garbageDTOS) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        OpResultDTO op = new OpResultDTO();
        for (GarbageDTO garbageDTO: garbageDTOS) {
            GarbageEntity garbageEntity = mapperFactory.getMapperFacade().map(garbageDTO, GarbageEntity.class);
            garbageEntity.setGmtCreate(new Date(System.currentTimeMillis()));
            garbageMapper.add(garbageEntity);
        }
        return op;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO add(GarbageDTO garbageDTO) throws Exception {
        OpResultDTO op = new OpResultDTO();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        GarbageEntity garbageEntity = mapperFactory.getMapperFacade().map(garbageDTO, GarbageEntity.class);
        garbageEntity.setGmtCreate(new Date(System.currentTimeMillis()));
        op.setResult(garbageMapper.add(garbageEntity));
        return op;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO update(GarbageDTO garbageDTO) throws Exception {
        OpResultDTO op = new OpResultDTO();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        GarbageEntity garbageEntity = mapperFactory.getMapperFacade().map(garbageDTO, GarbageEntity.class);
        op.setResult(garbageMapper.update(garbageEntity));
        return op;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO remove(Long garbageId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        op.setResult(garbageMapper.remove(garbageId));
        return op;
    }

    @Override
    public GarbageDTO queryOne() throws Exception {
        return garbageMapper.getRandom();
    }

    @Override
    public OpResultDTO checkOne(GarbageDTO garbageDTO) throws Exception {
        OpResultDTO op = new OpResultDTO();
        GarbageDTO garbageDTO1 = garbageMapper.getGarbageById(garbageDTO.getGarbageId());
        if (garbageDTO.getSortId().equals(garbageDTO1.getSortId())) {
            op.setMessage("yes");
        } else {
            op.setMessage("no");
            op.setResult(garbageMapper.getSort(garbageDTO1.getSortId()));
        }
        return op;
    }
}
