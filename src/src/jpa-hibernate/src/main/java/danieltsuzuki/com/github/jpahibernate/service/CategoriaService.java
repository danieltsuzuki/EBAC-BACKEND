package danieltsuzuki.com.github.jpahibernate.service;

import danieltsuzuki.com.github.jpahibernate.dto.CategoriaAtualizarDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaSalvarDto;
import danieltsuzuki.com.github.jpahibernate.exception.NotFoundException;
import danieltsuzuki.com.github.jpahibernate.mapper.CategoriaMapper;
import danieltsuzuki.com.github.jpahibernate.model.Categoria;
import danieltsuzuki.com.github.jpahibernate.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void salvar(CategoriaSalvarDto dto) {
        categoriaRepository.save(CategoriaMapper.toEntity(dto));
    }

    public void removerPorId(Long id) {
        if (!categoriaRepository.existsById(id))
            throw new NotFoundException("Categoria não encontrada com id: " + id);
        categoriaRepository.deleteById(id);
    }

    public Page<CategoriaDto> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(CategoriaMapper::toDto);
    }

    public CategoriaDto buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(CategoriaMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + id));
    }

    public void atualizar(CategoriaAtualizarDto dto, Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty())
            throw new NotFoundException("Categoria não encontrada com id: " + id);
        dto.setId(id);
        if (dto.getNome() == null)
            dto.setNome(categoria.get().getNome());
        if (dto.getDescricao() == null)
            dto.setDescricao(categoria.get().getDescricao());
        categoriaRepository.save(CategoriaMapper.toEntity(dto));
    }

}
