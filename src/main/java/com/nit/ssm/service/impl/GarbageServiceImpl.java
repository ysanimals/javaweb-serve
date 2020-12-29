package com.nit.ssm.service.impl;


import com.nit.ssm.dto.*;
import com.nit.ssm.entity.GarbageEntity;
import com.nit.ssm.mapper.GarbageMapper;
import com.nit.ssm.service.GarbageService;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.nit.ssm.utils.UploadFileUtil;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
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
                garbageFlag,
                garbageName,
                sortId);
        PagingDTO pagingDTO = new PagingDTO(
                req.getPageNo(),
                req.getPageSize(),
                count,
                garbageMapper.list4Table(
                        garbageFlag,
                        garbageName,
                        sortId,
                        req.getStart(),
                        req.getPageSize(),
                        req.getSortField(),
                        req.getSortOrder()));
        return new TableRspDTO(pagingDTO);
    }

    @Override
    public TableRspDTO statistics(TableReqDTO req) throws Exception {
        String garbageFlag = req.parseQueryParam("garbageFlag");
        String garbageName = req.parseQueryParam("garbageName");
        List<GarbageDTO> garbageDTOS = garbageMapper.statistics(
                garbageFlag,
                garbageName,
                req.getStart(),
                req.getPageSize(),
                req.getSortField(),
                req.getSortOrder());
        Long count = garbageMapper.countStatistics(
                garbageFlag,
                garbageName);
        PagingDTO pagingDTO = new PagingDTO(
                req.getPageNo(),
                req.getPageSize(),
                count,
                garbageDTOS);
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
    public OpResultDTO remove(Integer garbageId) throws Exception {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO uploadFile(MultipartFile file, Long garbageId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        String originalName = file.getOriginalFilename();
        if (originalName == null) {
            op.setMessage("error");
            op.setResult("文件有误");
        } else {
            String fileName = UUID.randomUUID().toString() + originalName.substring(originalName.lastIndexOf("."));
            String url = UploadFileUtil.uploadFile(file, fileName);
            garbageMapper.updateImage(garbageId, url, originalName);
            op.setResult(url);
        }
        return op;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO removeFile(GarbageDTO garbageDTO) throws Exception {
        OpResultDTO op = new OpResultDTO();
        UploadFileUtil.deleteTempFile(garbageDTO.getImageUrl());
        garbageMapper.removeImage(garbageDTO.getGarbageId());
        return op;
    }

}
