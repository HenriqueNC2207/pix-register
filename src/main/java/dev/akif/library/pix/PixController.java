package dev.akif.library.pix;
import dev.akif.crud.CRUDController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
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


    public PixController(final PixService service, final PixDTOMapper mapper) {
        super("Pix", service, mapper);
    }

}
