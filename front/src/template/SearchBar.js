import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Popover, Col, Row, Button, Form, InputGroup, OverlayTrigger } from 'react-bootstrap';
import RegisterModal from "./RegisterModal";
import LoginModal from './LoginModal'
import AddFranchiseeModal from './AddFranchiseeModal'



function SearchBar(){
  // let [show, setShow] = useState(false);

  // const callback=()=>{
  //   setShow(show=!show);
  // }

  const popover = (
    <Popover id="popover-basic">
      <Popover.Header as="h3">회원정보</Popover.Header>
      <Popover.Body>
        <p>이름 : ~~~</p>
        <p>사업자번호 : ~~~</p>
        <RegisterModal></RegisterModal>
        <LoginModal></LoginModal>
        
      </Popover.Body>
    </Popover>
  );

  return (
    <>
    <div className="searchArea"> 
    <Row>
      <Col md={10}>
      <InputGroup className="mb-4">
        <Col md={2}>
        <Form.Select aria-label="Default select">
          <option>-</option>
          <option value="1">음식점</option>
          <option value="2">카페</option>
          <option value="3">은행</option>
          <option value="4">etc2</option>
        </Form.Select>
        </Col>
        <Col md={8}>
          <Form.Control aria-label="Text input with dropdown button" placeholder="검색내용"/>
        </Col>
        <Col md={2}>
          <Button variant="outline-primary">검색</Button>
        </Col>
      </InputGroup>
      </Col>
      <Col md={2}>
        <OverlayTrigger trigger="click" placement="bottom" overlay={popover}>
          <Button variant="success">이미지</Button>
        </OverlayTrigger>
      </Col>

    </Row>
    </div>

    <div className={"characterImageArea"}>
        
    </div>
    
    </>
  );
}

export default SearchBar;
