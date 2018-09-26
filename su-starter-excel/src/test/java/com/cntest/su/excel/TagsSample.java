package com.cntest.su.excel;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.cntest.su.excel.model.Department;

public class TagsSample extends AbstractSample {
  @Test
  public void test() throws Exception {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("departments", Department.multi(3));

    Excel excel = excelFactory.create("tags", model);
    excel.writeTo(new FileOutputStream(outputDir + "/tags_export.xlsx"));
  }
}
