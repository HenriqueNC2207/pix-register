package dev.akif.library.pix;
import dev.akif.crud.CRUDController;
import dev.akif.crud.common.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/pix")
@RestController
@Tag(name = "pix", description = "CRUD operations for pix")
public class PixController extends CRUDController<
        UUID,
        PixEntity,
        Pix,
        PixDTO,
        CreatePix,
        UpdatePix,
        CreatePixDTO,
        UpdatePixDTO,
        PixMapper,
        PixDTOMapper,
        PixRepository,
        PixService> {

    private final PixService pixService;
    public PixController(final PixService service, final PixDTOMapper mapper) {
        super("Pix", service, mapper);
        this.pixService = service;
    }
    private static final String LIST_BOOKS_SUMMARY = "List books of author";
    private static final String LIST_BOOKS_DESCRIPTION = "End point para crição de chave pix";
    private static final String LIST_BOOKS_RESPONSE = "Chave pix criada.";


    @ApiResponse(responseCode = CODE_OK, description = LIST_BOOKS_RESPONSE)
    @ApiResponse(responseCode = "422", description = "Inclusão não realizada, não respeita condições")
    @PostMapping(path = "/CriarPix", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = LIST_BOOKS_SUMMARY, description = LIST_BOOKS_DESCRIPTION)
    public ResponseEntity<String> createUsingRepository(
            @Valid @RequestBody CreatePixDTO createPixDTO, // Adicione esta linha
            @Parameter(hidden = true)
            @PathVariable
            final Map<String, String> pathVariables,
            @Parameter(hidden = true)
            HttpServletRequest request
    ) {
        final var parameters = new Parameters(pathVariables, request);
        Instant now = Instant.now();
        PixEntity pixEntity = PixEntity.fromCreatePixDTO(createPixDTO, now);
        Object createdPix = pixService.createPix(pixEntity, parameters);
        if (createdPix instanceof PixEntity) {
            PixEntity createdEntity = (PixEntity) createdPix;
            if (createdEntity.getId() == null) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Chave criada não respeitou as condições necessárias." + createdPix.toString());
            }
            return ResponseEntity.ok("Chave Pix criado com sucesso. Id da chave é :" + createdEntity.getId().toString());
        
        } else if (createdPix instanceof String) {
            // Handle validation message
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body((String) createdPix);
        } else {
            // Handle unexpected type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type");
        }
    }
}
