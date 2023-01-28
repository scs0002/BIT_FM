import Footer from '../template/Footer'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Container,Row,Col } from 'react-bootstrap';

  function LoginFormDesign() {
    return (
      <Container style={{marginTop:'20%'}}>
        <Row>
            <Col></Col>
            <Col xs={5} className="Contents-Header">로그인</Col>
            <Col></Col>
        </Row>
        <Form className='LoginForm'>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Control type="email" placeholder="이메일" />
          </Form.Group>
    
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Control type="password" placeholder="비밀번호" />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicCheckbox">
            <Form.Check type="checkbox" label="아이디 저장" />
          </Form.Group>
          <Button variant="primary" type="submit" size="lg" id="Login-LoginForm__loginBtn">
            로그인
          </Button>
          <div className="register mb-3">
            <span><a href="#">회원가입</a><a className="search" href="#">아이디찾기/비밀번호찾기</a></span>
          </div>
        </Form>
      </Container>
    );
  }

  function Login() {
    return (
      <div className="Login">
        <LoginFormDesign></LoginFormDesign>
      </div>
    );
  }

export default Login;









