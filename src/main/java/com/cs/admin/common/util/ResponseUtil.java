package com.cs.admin.common.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.cs.admin.common.vo.ErrorVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/12 22:38
 */
@Slf4j
public class ResponseUtil {

    public static final String EXCEL_FORMAT = "xlsx";

    /**
     * 单例对象序列化
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * 写excel列表
     *
     * @param response 响应
     * @param title    标题
     * @param fileName 文件名
     * @param rows     数据列表
     */
    public static void writeExcel(HttpServletResponse response, String title, String fileName, List<Map<String, Object>> rows) {
        try (OutputStream out = response.getOutputStream()) {
            fileName = fileName + EXCEL_FORMAT;
            ExcelWriter writer = ExcelUtil.getWriter();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
            response.setHeader("fileName", URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "fileName");
            writer.merge(rows.size() - 1, title);
            writer.write(rows, true);
            writer.flush(out);
            writer.close();
            IoUtil.close(out);
        } catch (Exception e) {
            log.error("返回Excel异常:{}", ExceptionUtil.stacktraceToString(e));
        }
    }


    /**
     * 返回异常处理
     *
     * @param response response
     * @param fileName 文件名
     * @param bytes    数据
     * @throws IOException io异常
     */
    public static void writeBytesStream(HttpServletResponse response, String fileName, byte[] bytes) throws IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName + ".txt", "utf-8")));
        response.setCharacterEncoding("UTF-8");
        IoUtil.write(response.getOutputStream(), true, bytes);
    }

    /**
     * 返回异常处理
     *
     * @param response response
     * @param errorVO  错误VO
     * @throws IOException io异常
     */
    public static void writeJsonData(HttpServletResponse response, ErrorVO errorVO) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorVO.getStatus());
        OBJECT_MAPPER.writeValue(response.getWriter(), errorVO);
    }

}
