package socks.service.service;

import socks.service.entity.SocksEntity;
import socks.service.model.Socks;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class MappingSockService implements MappingService<SocksEntity, Socks> {

    @Override
    public Socks toModel(SocksEntity entity) {
        return new Socks(
                entity.getId(),
                '#' + Integer.toHexString(entity.getColor()).toUpperCase().substring(2),
                entity.getCotton(),
                entity.getAmount()
        );
    }

    @Override
    public SocksEntity toEntity(Socks model) {
        return new SocksEntity(
                Color.decode(model.getColor()).getRGB(),
                model.getCotton(),
                model.getAmount()
        );
    }
}
