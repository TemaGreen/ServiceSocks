package socks.service.service;

public interface MappingService<E, M> {

    M toModel(E entity);

    E toEntity(M model);
}
