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
package sample.mybatis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.mybatis.domain.City;
import sample.mybatis.domain.SysUserVO;
import sample.mybatis.mapper.CityMapper;
import sample.mybatis.mapper.SysUserMapper;

@RequestMapping("/sysUsers")
@RestController
public class SysUserRestController {

  private final SysUserMapper sysUserMapper;

  public SysUserRestController(SysUserMapper sysUserMapper) {
    this.sysUserMapper = sysUserMapper;
  }

  @GetMapping("{id}")
  SysUserVO getSysUser(@PathVariable String id) {
    return sysUserMapper.findById(id);
  }

  @GetMapping("/testadd")
  String testAddSysUser(){
    SysUserVO sysUserVO = new SysUserVO();
    Integer randomSign = (int)(Math.random()*100);
    sysUserVO.setName("test" + randomSign);
    sysUserVO.setAddress("test" + randomSign);
    sysUserVO.setPasswd(""+randomSign);
    sysUserVO.setPhone("1888888888" + randomSign);
    sysUserVO.setSex(1);
    sysUserVO.setCardId("5109283746550342" + randomSign);
    return "" + sysUserMapper.addTest(sysUserVO);
  }

}
