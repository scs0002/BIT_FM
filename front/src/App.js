import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import AddFranchisee from "./pages/AddFranchisee";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AddMenu from "./pages/AddMenu";
import Mypage from "./pages/Mypage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/franchisee" element={<AddFranchisee/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/menu" element={<AddMenu/>}/>
        <Route path="/mypage" element={<Mypage/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
