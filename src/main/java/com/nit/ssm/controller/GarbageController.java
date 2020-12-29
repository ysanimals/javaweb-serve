package com.nit.ssm.controller;

import com.nit.ssm.dto.GarbageDTO;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.service.GarbageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public OpResultDTO add(@RequestBody GarbageDTO garbageDTO){
        OpResultDTO op = new OpResultDTO();
        try{
            op = garbageService.add(garbageDTO);
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("添加失败");
            System.out.println(e.toString());
        }
        return op;
    }
    /**
     * 修改垃圾信息
     * @return OpResult
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OpResultDTO update(@RequestBody GarbageDTO garbageDTO) {
        OpResultDTO op = new OpResultDTO();
        try {
            op = garbageService.update(garbageDTO);
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

}
