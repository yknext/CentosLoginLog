package microserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import microserver.entity.SSHLoginPO;

public interface SSHLoginReposity extends JpaRepository<SSHLoginPO, Long>{
	
	@Query("select s from SSHLoginPO s where s.status=:status order by s.login_date desc")
	public Page<SSHLoginPO> findSSHLoginByDateDesc(@Param(value="status")int status,Pageable pageRequest);
	
}
