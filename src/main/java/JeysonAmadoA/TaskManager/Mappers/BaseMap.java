package JeysonAmadoA.TaskManager.Mappers;

import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import org.modelmapper.ModelMapper;

public abstract class BaseMap<D, E> {

    protected final ModelMapper modelMapper = new ModelMapper();

    private final Class<D> dtoClass;
    private final Class<E> entityClass;

    public BaseMap(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }
}