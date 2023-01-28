import '../App.css';
import {Container,Form,Row,Col,Image,Button,InputGroup } from 'react-bootstrap'
import useDaumPostcodePopup from 'react-daum-postcode/lib/useDaumPostcodePopup';
import 'bootstrap/dist/css/bootstrap.css';
import Header from '../template/Header';
import Footer from '../template/Footer';

function AddFranchisee() {
  let scriptUrl = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
  const open = useDaumPostcodePopup(scriptUrl);

  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
  };

  const handleClick = () => {
    open({ onComplete: handleComplete });
  };

  return (
    <>
      <Container>
        <Row>
          <Col>
            <h2 style={{textAlign:'center'}}>상권 등록</h2>
          </Col>
        </Row>

        <Row>
          <Form>
            <Row>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>사업자 번호</Form.Label>
                <Form.Control type="text" placeholder="사업자 번호" />
              </Form.Group>
            </Row>
            <Row>
                <Col sm={4}>
                  <Image src='https://dummyimage.com/100x150/000/fff&text=test'></Image>
                  <Form.Control type="file" className='file mt-3'/>
                </Col>
                <Col>
                    <Form.Label>가게 명</Form.Label>
                    <Form.Control type="text" placeholder="가게 이름" />
                    <Form.Label>대표자</Form.Label>
                    <Form.Control type="text" placeholder="대표자 이름" />
                </Col>
            </Row>

            <Row className='franchiseeadd-container--post'>
              <Row>
                <Col className='mt-3'>
                  <InputGroup style={{width:'300px'}}>
                    <Form.Control
                      type='text'
                      placeholder='우편번호'
                    />
                    <Button variant="primary" onClick={handleClick}>우편번호 검색</Button>
                  </InputGroup>
                  <Form.Control 
                      type='text'
                      placeholder='상세'
                  />
                  <Form.Control 
                      type='text'
                      placeholder='상세 주소'
                  />
                </Col>
              </Row>

            </Row>
            <Row>
              <Row>
                <Col>
                  <Form.Label>가맹점 소개 </Form.Label>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Control
                    as="textarea"
                    placeholder="가맹점 소개글을 써주세요"
                    style={{ height: '100px' }}
                  />

                </Col>
              </Row>
            </Row>
          </Form>          
        </Row>
      </Container>
    </>
  );
}

export default AddFranchisee;
