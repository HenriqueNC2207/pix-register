package dev.akif.library.pix;

import dev.akif.crud.CRUDCreateDTO;
import jakarta.validation.constraints.NotNull;

public record CreatePixDTO(
        @NotNull String tipoChave,
        @NotNull String valorChave,
        @NotNull String tipoConta,
        @NotNull String numAgencia,
        @NotNull String numConta,
        @NotNull String nomeCorrentista,
        String sobrenomeCorrentista,
        @NotNull String tipoPessoa
) implements CRUDCreateDTO {
}
