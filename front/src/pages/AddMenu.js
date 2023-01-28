import '../App.css';
// import Container from 'react-bootstrap/Container';
import {Container,Row,Col,Button, ListGroup, Card, Modal, Form, FloatingLabel} from 'react-bootstrap';
import { useState } from 'react';
import Header from '../template/Header'
import Footer from '../template/Footer'

function Contents() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <Container className="Contents-Container">
      <Row>
          <Col></Col>
          <Col xs={5} className="Contents-Header">메뉴</Col>
          <Col></Col>
      </Row>
      <Row>
          <Col>
          {/* <Button variant="primary" onClick={handleShow}>
            메뉴 추가
          </Button> */}
          <button id="AddMenu--menuAddBtn" onClick={handleShow}>메뉴 추가</button>

          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>메뉴 추가</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form>
              <div className="ex-layout">
                <div className="main">
                  <div className="left-menu">
                    <img src="https://dummyimage.com/200x220/000/fff.png&text=food" alt="..." /><br/>
                  </div>
                  <div className="content">
                    <div className="article">
                      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>메뉴 이름</Form.Label>
                        <Form.Control type="text" placeholder="메뉴 이름을 적어주세요." autoFocus />
                      </Form.Group>
                    </div>
                    <div className="comment">
                      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                          <Form.Label>메뉴 가격</Form.Label>
                          <Form.Control type="text" placeholder="메뉴 가격을 적어주세요." autoFocus />
                      </Form.Group>
                    </div>
                    <div className="comment">
                      <Form.Group controlId="formFile" className="mb-3 filebox">
                        <Form.Label for="ex_file">이미지 업로드</Form.Label>
                        <Form.Control type="file" id="ex_file" />
                      </Form.Group>
                    </div>
                  </div>
                </div>
              </div>
              </Form>
              <FloatingLabel controlId="floatingTextarea2" label="메뉴 소개">
                <Form.Control as="textarea" placeholder="Leave a comment here" style={{ height: '150px' }} />
              </FloatingLabel>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                뒤로
              </Button>
              <Button variant="primary" onClick={handleClose}>
                등록
              </Button>
            </Modal.Footer>
          </Modal>
              </Col>
        </Row>
        <Row id="Contents-Container--MenuList">
          {/* <ListGroup >
            <ListGroup.Item active>메뉴</ListGroup.Item>
                {Array.from({ length: 10 }).map((_, idx) => (
                  <ListGroup.Item >
                    <Row xs={2} md={5}>
                      <Col >
                        <Card className="Contents-Container--MenuList__Menu">
                          <Card.Img variant="top" src="https://dummyimage.com/200x200/000/fff.png&text=food" />
                          <Card.Body>
                            <Card.Title>햄버거</Card.Title>
                            <Card.Text>3000원</Card.Text>
                          </Card.Body>
                        </Card>
                      </Col>
                    </Row>
                  </ListGroup.Item>
                ))}
          </ListGroup> */}
          <ListGroup>            
          <ListGroup.Item active id="AddMenu--menuBar">메뉴</ListGroup.Item>
            <Row>
              {Array.from({ length: 9 }).map((_, idx) => (
                    <Col>
                    <ListGroup.Item disabled>
                            <Card className="Contents-Container--MenuList__Menu">
                            <Card.Img variant="top" className="Contents-Container--MenuList__MenuImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUWviag2_CnWRnYaZfjloZKd5wFrill3tb3w&usqp=CAU" />
                              <Card.Body>
                                <Card.Title>햄버거</Card.Title>
                                <Card.Text>3000원</Card.Text>
                              </Card.Body>
                            </Card>
                    </ListGroup.Item>
                  </Col>
                  ))}
            </Row>
            {/* <Row id="test">
              <Col>
                <ListGroup.Item disabled>
                        <Card className="Contents-Container--MenuList__Menu">
                        <Card.Img variant="top" class="Contents-Container--MenuList__MenuImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUWviag2_CnWRnYaZfjloZKd5wFrill3tb3w&usqp=CAU" />
                          <Card.Body>
                            <Card.Title>햄버거</Card.Title>
                            <Card.Text>3000원</Card.Text>
                          </Card.Body>
                        </Card>
                </ListGroup.Item>
              </Col>
              <Col>
                <ListGroup.Item>
                        <Card className="Contents-Container--MenuList__Menu">
                        <Card.Img variant="top" class="Contents-Container--MenuList__MenuImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUWviag2_CnWRnYaZfjloZKd5wFrill3tb3w&usqp=CAU" />
                          <Card.Body>
                            <Card.Title>햄버거</Card.Title>
                            <Card.Text>3000원</Card.Text>
                          </Card.Body>
                        </Card>
                </ListGroup.Item>
              </Col>
              <Col>
              <ListGroup.Item>
                        <Card className="Contents-Container--MenuList__Menu">
                        <Card.Img variant="top" class="Contents-Container--MenuList__MenuImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUWviag2_CnWRnYaZfjloZKd5wFrill3tb3w&usqp=CAU" />
                          <Card.Body>
                            <Card.Title>햄버거</Card.Title>
                            <Card.Text>3000원</Card.Text>
                          </Card.Body>
                        </Card>
                </ListGroup.Item>
              </Col>
            </Row> */}
          </ListGroup>
        </Row>
    </Container>
  );
}

function AddMenu() {
  return (
    <div className="App">
      <Header></Header>
      <Contents></Contents>
      <Footer></Footer>
    </div>
  );
}

export default AddMenu;

