import React, { useState } from 'react'
import Login from '../pages/Login';
import { Modal, ModalBody, Button, ModalFooter } from 'react-bootstrap';

export default function LoginModal() {
    const [show,setShow] = useState(false);

    const handleClose=()=>{
        setShow(false);
    }
    const handleShow=()=>{
        setShow(true);
    }
 
  return (
    <>
        <Button onClick={handleShow}>로그인</Button>
        <Modal
            show={show}
            isHide={handleClose}
            keyboard={false}
        >
        <ModalBody>
            <Login></Login>
        </ModalBody>
        <ModalFooter>
            <Button variant='primary' onClick={handleClose} >로그인</Button>
        </ModalFooter>
        </Modal>
    </>
  )
}
