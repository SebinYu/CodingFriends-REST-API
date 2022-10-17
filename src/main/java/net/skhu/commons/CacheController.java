//package net.skhu.commons;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CacheController {
//    private final CacheService cacheService;
//
//    @GetMapping("/cache/test")
//    public ResponseEntity<String> getCacheData(CacheDto cacheDto){
//        return new ResponseEntity<>(cacheService.isUsingCacheInfo(cacheDto.getId()), HttpStatus.OK);
//    }
//
//    @Getter
//    @Setter
//    class CacheDto{
//        private Integer id;
//    }
//}
