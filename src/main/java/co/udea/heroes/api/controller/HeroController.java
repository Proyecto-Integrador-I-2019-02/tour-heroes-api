package co.udea.heroes.api.controller;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);
    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){

        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping()
    @ApiOperation(value = "Buscar todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @PostMapping()
    @ApiOperation(value = "Añadir heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe añadido correctamente", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Eliminar Heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El heroe fue eliminado correctamente", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(heroService.deleteHero(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Actualizar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El fueron actualizados correctamente", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> update( @RequestBody Hero hero) {
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    @GetMapping("/")
    @ApiOperation(value = "Buscar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(String name){
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }


}
