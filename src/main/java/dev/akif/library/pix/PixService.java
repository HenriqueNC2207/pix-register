package dev.akif.library.pix;

import dev.akif.crud.CRUDService;
import dev.akif.crud.common.InstantProvider;
import dev.akif.crud.common.Parameters;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PixService extends CRUDService<UUID, PixEntity,Pix, CreatePix, UpdatePix, PixRepository, PixMapper> {
    public PixService(final InstantProvider instantProvider, final PixRepository repository, final PixMapper mapper) {
        super("Pix", instantProvider, repository, mapper);
    }
  

    @Override
    protected PixEntity createUsingRepository(final PixEntity pixEntity, final Parameters parameters) {
        return getRepository().save(pixEntity);
    }

    @Override
    protected PixEntity getUsingRepository(UUID arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsingRepository'");
    }

    @Override
    protected int updateUsingRepository(PixEntity arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsingRepository'");
    }


    @Override
    protected Page<PixEntity> listUsingRepository(final Pageable pageable, final Parameters parameters) {
        return getRepository().findAllByDeletedAtIsNull(pageable);
    }
}
