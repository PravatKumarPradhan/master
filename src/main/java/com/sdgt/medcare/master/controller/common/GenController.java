package com.sdgt.medcare.master.controller.common;/*
 * package com.sdgt.medcare.master.controller;
 *
 * import java.io.Serializable; import java.util.List; import
 * java.util.Optional;
 *
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Example; import
 * org.springframework.data.domain.Page; import
 * org.springframework.data.domain.Pageable; import
 * org.springframework.data.domain.Sort; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 *
 * import com.sdgt.medcare.master.repository.UnitIdRepository;
 *
 * @RestController
 *
 * @CrossOrigin
 *
 * @RequestMapping("Unit") public class GenController {
 *
 * @Autowired private UnitIdRepository unitIdRepository;
 *
 *
 * public GenController() { this.unitIdRepository=new UnitIdRepository<T,
 * Serializable>() {
 *
 * @Override public <T> List<T> findAll() { // TODO Auto-generated method stub
 * return null; }
 *
 * @Override public List<T> findAll(Sort sort) { // TODO Auto-generated method
 * stub return null; }
 *
 * @Override public List<T> findAllById(Iterable<Serializable> ids) { // TODO
 * Auto-generated method stub return null; }
 *
 * @Override public <S extends T> List<S> saveAll(Iterable<S> entities) { //
 * TODO Auto-generated method stub return null; }
 *
 * @Override public void flush() { // TODO Auto-generated method stub
 *
 * }
 *
 * @Override public <S extends T> S saveAndFlush(S entity) { // TODO
 * Auto-generated method stub return null; }
 *
 * @Override public void deleteInBatch(Iterable<T> entities) { // TODO
 * Auto-generated method stub
 *
 * }
 *
 * @Override public void deleteAllInBatch() { // TODO Auto-generated method stub
 *
 * }
 *
 * @Override public T getOne(Serializable id) { // TODO Auto-generated method
 * stub return null; }
 *
 * @Override public <S extends T> List<S> findAll(Example<S> example) { // TODO
 * Auto-generated method stub return null; }
 *
 * @Override public <S extends T> List<S> findAll(Example<S> example, Sort sort)
 * { // TODO Auto-generated method stub return null; }
 *
 * @Override public Page<T> findAll(Pageable pageable) { // TODO Auto-generated
 * method stub return null; }
 *
 * @Override public <S extends T> S save(S entity) { // TODO Auto-generated
 * method stub return null; }
 *
 * @Override public Optional<T> findById(Serializable id) { // TODO
 * Auto-generated method stub return null; }
 *
 * @Override public boolean existsById(Serializable id) { // TODO Auto-generated
 * method stub return false; }
 *
 * @Override public long count() { // TODO Auto-generated method stub return 0;
 * }
 *
 * @Override public void deleteById(Serializable id) { // TODO Auto-generated
 * method stub
 *
 * }
 *
 * @Override public void delete(T entity) { // TODO Auto-generated method stub
 *
 * }
 *
 * @Override public void deleteAll(Iterable<? extends T> entities) { // TODO
 * Auto-generated method stub
 *
 * }
 *
 * @Override public void deleteAll() { // TODO Auto-generated method stub
 *
 * }
 *
 * @Override public <S extends T> Optional<S> findOne(Example<S> example) { //
 * TODO Auto-generated method stub return null; }
 *
 * @Override public <S extends T> Page<S> findAll(Example<S> example, Pageable
 * pageable) { // TODO Auto-generated method stub return null; }
 *
 * @Override public <S extends T> long count(Example<S> example) { // TODO
 * Auto-generated method stub return 0; }
 *
 * @Override public <S extends T> boolean exists(Example<S> example) { // TODO
 * Auto-generated method stub return false; }
 *
 * @Override public Page<T> findByUnitMasterId(Long unitMaster, Pageable
 * pageable) { // TODO Auto-generated method stub return null; }
 *
 * @Override public List<com.sdgt.medcare.master.controller.T> findAll() { //
 * TODO Auto-generated method stub return null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> List<S>
 * saveAll(Iterable<S> entities) { // TODO Auto-generated method stub return
 * null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> S
 * saveAndFlush(S entity) { // TODO Auto-generated method stub return null; }
 *
 * @Override public com.sdgt.medcare.master.controller.T getOne(Serializable id)
 * { // TODO Auto-generated method stub return null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> List<S>
 * findAll(Example<S> example) { // TODO Auto-generated method stub return null;
 * }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> List<S>
 * findAll(Example<S> example, Sort sort) { // TODO Auto-generated method stub
 * return null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> S save(S
 * entity) { // TODO Auto-generated method stub return null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> Optional<S>
 * findOne(Example<S> example) { // TODO Auto-generated method stub return null;
 * }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> Page<S>
 * findAll(Example<S> example, Pageable pageable) { // TODO Auto-generated
 * method stub return null; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> long
 * count(Example<S> example) { // TODO Auto-generated method stub return 0; }
 *
 * @Override public <S extends com.sdgt.medcare.master.controller.T> boolean
 * exists(Example<S> example) { // TODO Auto-generated method stub return false;
 * } };
 *
 * }
 *
 * }
 */
