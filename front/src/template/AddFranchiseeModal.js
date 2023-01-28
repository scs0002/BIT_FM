import React, { useState } from 'react'
import { Button,Modal, ModalBody, ModalFooter } from 'react-bootstrap';
import Addfranchisee from '../pages/AddFranchisee'

export default function LoginModal() {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
 
  return (
    <>
        <Button onClick={handleShow}>프렌차이즈 등록</Button>
        <Modal
            show={show}
            onHide={handleClose}
            keyboard={false}
        >
            <ModalBody>
                <Addfranchisee></Addfranchisee>
            </ModalBody>
            <ModalFooter>
                <Button variant='primary' onClick={handleClose}>다음</Button>
            </ModalFooter>
        </Modal>
    </>
  )
}
