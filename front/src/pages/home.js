import React, { useState } from "react";
import NaverApiMap from "../apis/NaverAPIMap";
import MapOffCanvase from '../template/MapOffCanvase';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
import SearchBar from '../template/SearchBar';
import '../template/SearchBar.css';

function Home(){
  // offcanvase 옵션 배경 스크롤 허용 클릭 허용
  const options = 
    {
      name: 'Enable body scrolling',
      scroll: true,
      backdrop: false,
    }
  
  let [toggle, setToggle] = useState(false);

  const callback = () => {
    setToggle(toggle=!toggle);
  }

  return (
    <div className="outline">
      <NaverApiMap callback={callback}></NaverApiMap>
      <MapOffCanvase options={options} tog={{toggle,setToggle}} />
      <div className="searchBarContainer">
        <SearchBar></SearchBar>
        <Link to="/login"><Button>로그인</Button></Link>
        <Link to="/register"><Button>회원가입</Button></Link>
        <Link to="/franchisee"><Button>상권등록 페이지</Button></Link>
        <Link to="/menu"><Button>메뉴등록 페이지</Button></Link>
        <Link to="/mypage"><Button>마이 페이지</Button></Link>
      </div>
      <div className="container">
        
      </div>
    </div>
  );
}

export default Home;
