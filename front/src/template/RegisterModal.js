import React, { useState } from 'react'
import Register from '../pages/Register';
import { Button, Modal, ModalBody, ModalFooter } from 'react-bootstrap';

export default function RegisterModal() {

  const [show,setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
        <Button onClick={handleShow}>회원가입</Button>
        <Modal
            show={show}
            onHide={handleClose}
            keyboard={false}
        >
            <ModalBody>
                <Register></Register>
            </ModalBody>
            <ModalFooter>
                <Button variant='primary' onClick={handleClose}>회원가입</Button>
            </ModalFooter>
        </Modal>
    </>
  )
}
