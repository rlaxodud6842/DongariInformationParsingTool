package practice.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class PoiMakeExcel {

    public static String filePath = "C:\\poi_temp";
    public static String fileNm = "poi_making_file_test.xlsx";

    public static void main(String[] args) {

        // 빈 Workbook 생성
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 빈 Sheet를 생성
        XSSFSheet sheet = workbook.createSheet("employee data");

        // Sheet를 채우기 위한 데이터들을 Map에 저장
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Name", "Major", "SchoolNumber","Grade"});
        Scanner sc = new Scanner(System.in);
        int counter = 2;
        while (true){
            System.out.println("q 입력시 종료\n입력:");
            String information = sc.nextLine();
            if (information.equals("q")){
                break;
            }else{
                ParsingInfomation parsingTool = new ParsingInfomation(information);
                String [] parsedArray = parsingTool.parsing(parsingTool.getInformation());
                try{data.put(String.valueOf(counter++),new Object[]{parsedArray[0],parsedArray[1],parsedArray[2],parsedArray[3]});
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("빠진 정보가 있어보입니다.");
                }catch (Exception e){
                    System.out.println("잘못된 접근");
                }
            }
        }
        // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
        Set<String> keyset = data.keySet();
        int rownum = 0;

        // 알아야할 점, TreeMap을 통해 생성된 keySet는 for를 조회시, 키값이 오름차순으로 조회된다.
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String)obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer)obj);
                }
            }
        }

        try (FileOutputStream out = new FileOutputStream(new File(filePath, fileNm))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}