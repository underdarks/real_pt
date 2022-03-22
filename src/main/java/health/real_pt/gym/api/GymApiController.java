package health.real_pt.gym.api;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import health.real_pt.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/gym")
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

        Optional<Gym> gymOptional = gymService.findOne(id);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체를 찾을 수 없습니다!"));

        return new GymDto().entityToDto(gym);
    }


//    @GetMapping("/{id}")
//    public GymDto findGym(@PathVariable("id") Long id){
//
//    }
//
//    @GetMapping("")
//    public GymDto findAllGym(){
//
//    }


}
