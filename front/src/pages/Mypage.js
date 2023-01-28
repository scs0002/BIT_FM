import React from 'react'
import {Container,Row,Col,Image,Button, Table } from 'react-bootstrap'
import Header from '../template/Header';
import Footer from '../template/Footer';

function MypageForm() {
  return (
    <Container>
        <Row className='m-3'>
            <Col style={{textAlign:'center',borderBottom:'solid 1px gray'}}>
                <h2>마이 페이지</h2>
            </Col>
            <Col style={{textAlign:'center',borderBottom:'solid 1px gray'}}>
                <h2>사업장 리스트</h2>
            </Col>
        </Row>
        <Row className='m-3'>
            <Col className='d-flex'>
                <div className='d-grid gap-2' >
                    <Image src='https://dummyimage.com/624x400/000/fff&text=test'></Image>
                    <Button variant='outline-primary'>회원정보 수정</Button>
                    {/* <Button variant='outline-primary'>사업자 등록하기</Button> */}
                </div>
            </Col>
            <Col>
                <Row>
                    <Col >
                        <h3 style={{textAlign:'center'}}>개인정보</h3>
                        <h3 style={{textAlign:'center'}}></h3>
                        <Table striped bordered hover>
                            <thead style={{textAlign:'center'}}>
                                <tr>
                                    <th>이메일</th>
                                    <th>abcd@naver.com</th>
                                </tr>                               
                                <tr>
                                    <th>핸드폰 번호</th>
                                    <th>010-1234-5678</th>
                                </tr>                               
                                <tr>
                                    <th>등급</th>
                                    <th>일반사용자 or 사업자</th>
                                </tr>                               
                            </thead>
                        </Table>
                        <h3 style={{textAlign:'center'}}>작성한 댓글 리스트(임시)</h3>
                        <Table striped bordered hover>
                            <thead style={{textAlign:'center'}}>
                                <tr>
                                    <th>상가1</th>
                                    <th>ㅁㅁㄴㅇㄻㄴㅇㄹㄹㄹ</th>
                                </tr>                               
                                <tr>
                                    <th>상가2</th>
                                    <th>ㅁㄴㅇㄻㄴㅇㄻ</th>
                                </tr>                               
                                <tr>
                                    <th>상가3</th>
                                    <th>여기까지 입니다.</th>
                                </tr>                               
                            </thead>
                        </Table>
                    </Col>
                </Row>
            </Col>
        </Row>
       
       
    </Container>
)
}

function Mypage(){
    return (
        <>
            <Header></Header>
            <MypageForm></MypageForm>
            {/* <Footer></Footer> */}
        </>
    )
}

export default Mypage;