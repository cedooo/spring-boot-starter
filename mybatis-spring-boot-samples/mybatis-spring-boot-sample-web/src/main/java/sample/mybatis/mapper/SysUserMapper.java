/**
 *    Copyright 2015-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package sample.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sample.mybatis.domain.City;
import sample.mybatis.domain.SysUserVO;

/**
 * @author Eddú Meléndez
 */
@Mapper
public interface SysUserMapper {

  @Select("select id, name, sex, phone, address, card_id as cardId, passwd from sys_user where id = #{id}")
  SysUserVO findById(@Param("id") String id);

  @Insert("insert into sys_user (name, sex, phone, address, card_id, passwd) values (#{name}, #{sex}, #{phone}, #{address}, #{cardId}, #{passwd})")
  Integer addTest(SysUserVO sysUserVO);
}
