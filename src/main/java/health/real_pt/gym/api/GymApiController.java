package health.real_pt.gym.api;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import health.real_pt.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/gym")
@RequiredArgsConstructor
public class GymApiController {

    private final GymService gymService;

    /**
     * 헬스장 정보 저장
     * @param reqGymDto : 저장할 헬스장 정보 DTO
     * @return : ID(PK)값
     */
    @PostMapping("")
    public Long saveGym(@RequestBody @Valid GymDto reqGymDto){
        return gymService.saveGym(reqGymDto);
    }


    /**
     * 헬스장 정보 수정
     * @param id        : 수정할 ID(PK)
     * @param updGymDto : 수정할 헬스장 정보 DTO
     * @return          : 수정된 DTO
     */
    @PatchMapping("/{id}")
    public GymDto updateGym(@PathVariable("id") Long id, @RequestBody @Valid GymDto updGymDto){
        gymService.updateGym(updGymDto);
        Gym gym = gymService.findOne(id).orElseThrow(() -> new NoSuchElementException("Gym 객체를 찾을 수 없습니다!"));

        return new GymDto().entityToDto(gym);
    }

    /**
     * 특정 헬스장 조회
     * @param id : 헬스장 ID(PK)
     * @return   : GymDTO
     */
    @GetMapping("/{id}")
    public GymDto findGym(@PathVariable("id") Long id){
        Gym gym = gymService.findOne(id).orElseThrow(() -> new NoSuchElementException("Gym 객체를 찾을 수 없습니다!"));

        return new GymDto().entityToDto(gym);
    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @GetMapping("")
    public GymResultDto findAllGym(){
        List<Gym> findGyms = gymService.findGyms();

        List<GymDto> gymDtoList = findGyms.stream()
                .map(gym -> new GymDto().entityToDto(gym))
                .collect(Collectors.toList());


        return new GymResultDto(gymDtoList.size(),gymDtoList);
    }


    @DeleteMapping("/{id}")
    public String deleteGym(@PathVariable("id") Long id){
        gymService.deleteGym(id);

        return "success";
    }


}
