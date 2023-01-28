import React, { useState } from 'react';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import '../template/SearchBar.css';

function MapOffCanvase({options, tog}) {
  // console.log(tog.toggle);
  // const [show, setShow] = useState(false);

  const handleClose = () => tog.setToggle(false);
  // const toggleShow = () => setShow((s)=>{callback}); 
  const toggleShow = () => tog.setToggle((s) => !s);

  return (
    <>
      <Offcanvas className={'OffcanvasArea'} show={tog.toggle} onHide={handleClose} {...options}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>검색 키워드 입력 창</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body className={'offcanvas-body--bodyzone'}>
          <div className="offcanvas-body--titlezone">
            <div>
              <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160504_295%2Fzzlidde_1462360339348GT0M2_PNG%2F2016-05-04_20.11.40.png&type=sc960_832" width='200px' />
            </div>
            <div>
              <p>가게설명</p>
            </div>
          </div>
          <div className="offcanvas-body--buttonzone">
            <Tabs
              defaultActiveKey="home"
              className="mb-2 offcanvas-body--buttontab"
            >
              <Tab eventKey="home" title="정보">
                  <div>
                    <p>가게이름</p>
                    <p>식당이름입니다~</p>
                  </div>
                  <div>
                    <p>주소</p>
                    <p>주소입니다~</p>
                  </div>
                  <div>
                    <p>전화번호</p>
                    <p>전화번호입니다~</p>
                  </div>
                  <div>
                    <p>영업시간</p>
                    <p>영업시간입니다~</p>
                  </div>
              </Tab>
              <Tab eventKey="profile" title="메뉴">
                  <div>
                    <p>메뉴사진</p>
                    <p>식당이름입니다~</p>
                    <p>메뉴설명입니다~</p>
                  </div>
                  <div>
                    <p>메뉴사진</p>
                    <p>식당이름입니다~</p>
                    <p>메뉴설명입니다~</p>
                  </div>
                  <div>
                    <p>메뉴사진</p>
                    <p>식당이름입니다~</p>
                    <p>메뉴설명입니다~</p>
                  </div>
              </Tab>
              <Tab eventKey="contact" title="리뷰">
                  <div>
                    <p>리뷰제목 / 작성자</p>
                    <p>리뷰입니다~</p>
                  </div>
              </Tab>
            </Tabs>
          </div>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

export default MapOffCanvase;