package health.real_pt;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FIleTest {

    @Test
    public void 파일삭제_테스트(){
        //given

        String p="D:/upload_image/member/aaa.PNG";
        String fp = StringUtils.cleanPath(p);

        Path path = Paths.get(fp);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //when


        //then
    }
}
