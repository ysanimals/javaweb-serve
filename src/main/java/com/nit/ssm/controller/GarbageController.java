package com.nit.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.GarbageDTO;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.service.GarbageService;
import com.nit.ssm.utils.HttpRequestReader;
import com.nit.ssm.utils.UploadFileUtil;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/garbage")
public class GarbageController {
    @Resource
    private GarbageService garbageService;
    /**
     * 查询所有垃圾信息
     */
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    public OpResultDTO listAll(HttpServletRequest request){
        OpResultDTO op = new OpResultDTO();
        try{
            op = garbageService.listAllGarbage();
        } catch (Exception e) {
            op.setMessage("error");
            op.setMessage("查询失败");
            System.out.println(e.toString());
        }
        return op;
    }

    /**
     * 查询垃圾信息封装便于前端展示
     * @return OpResult
     */
    @RequestMapping(value = "/list4Table", method = RequestMethod.POST)
    public TableRspDTO list4Table(@RequestBody TableReqDTO req) {
        TableRspDTO rsp;
        try {
            rsp = garbageService.list4Table(req);
        } catch (Exception e) {
            rsp = new TableRspDTO();
            System.out.println(e.toString());
        }
        return rsp;
    }

    /**
     * 查询垃圾统计信息
     * @return OpResult
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public TableRspDTO statistics(@RequestBody TableReqDTO req) {
        TableRspDTO rsp;
        try {
            rsp = garbageService.statistics(req);
        } catch (Exception e) {
            rsp = new TableRspDTO();
            System.out.println(e.toString());
        }
        return rsp;
    }
    /**
     * 批量导入垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    public OpResultDTO addList(@RequestBody List<GarbageDTO> garbageDTOList){
        OpResultDTO op = new OpResultDTO();
        try {
            op = garbageService.addList(garbageDTOList);
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("导入失败");
            System.out.println(e.toString());
        }
        return op;
    }
    /**
     * 增加垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)//其他层抛出异常在controller中被捕获
    public OpResultDTO add( @RequestParam(value = "file",required = false)MultipartFile file,
                            @RequestParam("garbageFlag")String  garbageFlag,
                            @RequestParam("garbageName")String garbageName,
                            @RequestParam("sortId")Integer sortId){
        OpResultDTO op = new OpResultDTO();
        try{
            GarbageDTO garbageDTO = new GarbageDTO();
            garbageDTO.setSortId(sortId);
            garbageDTO.setGarbageName(garbageName);
            garbageDTO.setGarbageFlag(garbageFlag);
            if(file != null)
            {
                op = garbageService.add(garbageDTO,file);
            }else{
                op = garbageService.add(garbageDTO,null);
            }

        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("添加失败");
            System.out.println(e.toString());
        }
        return op;
    }


    /**
     * 增加垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)//其他层抛出异常在controller中被捕获
    public OpResultDTO update(
            @RequestParam(value = "file",required = false)MultipartFile file,
            @RequestParam("garbageId")Integer  garbageId,
            @RequestParam("garbageFlag")String  garbageFlag,
            @RequestParam("garbageName")String garbageName,
            @RequestParam("sortId")Integer sortId){
        OpResultDTO op = new OpResultDTO();
        try{
            GarbageDTO garbageDTO = new GarbageDTO();
            garbageDTO.setSortId(sortId);
            garbageDTO.setGarbageId(garbageId);
            garbageDTO.setGarbageName(garbageName);
            garbageDTO.setGarbageFlag(garbageFlag);
            if(file != null)
            {
                op = garbageService.update(garbageDTO,file);
            }else{
                op = garbageService.update(garbageDTO,null);
            }

        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("修改失败");
            System.out.println(e.toString());
        }
        return op;
    }



    /**
     * 修改垃圾信息
     * @return OpResult
     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public OpResultDTO update(@RequestBody GarbageDTO garbageDTO) {
//        OpResultDTO op = new OpResultDTO();
//        try {
//            op = garbageService.update(garbageDTO);
//        } catch (Exception e) {
//            op.setMessage("error");
//            op.setResult("修改失败");
//            System.out.println(e.toString());
//        }
//        return op;
//    }
    /**
     * 修改垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public OpResultDTO remove(@RequestBody Integer garbageId) {
        OpResultDTO op = new OpResultDTO();
        try {
            op = garbageService.remove(Integer.valueOf(garbageId));
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("删除失败");
            System.out.println(e.toString());
        }
        return op;
    }
    /**
     * 查询一条不带答案的垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public GarbageDTO queryOne() {
        GarbageDTO garbageDTO;
        try {
            garbageDTO = garbageService.queryOne();
        } catch (Exception e) {
            garbageDTO = null;
            System.out.println(e.toString());
        }
        return garbageDTO;
    }
    /**
     * 检查答案是否正确
     * @return OpResult
     */
    @RequestMapping(value = "/checkOne", method = RequestMethod.POST)
    public OpResultDTO checkOne(@RequestBody GarbageDTO garbageDTO) {
        OpResultDTO op;
        try {
            op = garbageService.checkOne(garbageDTO);
        } catch (Exception e) {
            op = null;
            System.out.println(e.toString());
        }
        return op;
    }

    /**
     * 上传文件
     * @return OpResult
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public OpResultDTO uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("garbageId")Integer garbageId) {
        OpResultDTO op;
        try {
            op = garbageService.uploadFile(file, garbageId);
        } catch (Exception e) {
            op = new OpResultDTO();
            op.setMessage("error");
            op.setResult("上传失败");
            System.out.println(e.toString());
        }
        return op;
    }


    /**
     * 上传文件
     * @return OpResult
     */
    @RequestMapping(value = "/removeFile", method = RequestMethod.POST)
    public OpResultDTO removeFile(@RequestBody GarbageDTO garbageDTO) {
        OpResultDTO op;
        try {
            op = garbageService.removeFile(garbageDTO);
        } catch (Exception e) {
            op = new OpResultDTO();
            op.setMessage("error");
            op.setResult("删除失败");
            System.out.println(e.toString());
        }
        return op;
    }

}
