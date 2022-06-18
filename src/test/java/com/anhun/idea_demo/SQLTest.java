package com.anhun.idea_demo;

import com.anhun.idea_demo.service.IInsertService;

import java.io.*;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SQLTest {

    //    @Autowired
    private IInsertService iInsertService;

    //    @Test
    public void insertImg() throws IOException {

        int id = 1;
        int len;
        byte[] buffer = new byte[1024];
        InputStream in;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(new File("D:\\Maven_workspace\\IDEA_demo\\src\\test\\resources", "J20_future.jpg"));

            while ((len = in.read(buffer)) != -1) {
                bout.write(buffer, 0, len);
            }
            bout.flush();
            byte[] img = bout.toByteArray();
            String text = "J20_future.jpg";

            iInsertService.insertImgandText(id, img, text);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
