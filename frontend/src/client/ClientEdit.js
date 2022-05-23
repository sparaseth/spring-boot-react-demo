import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";

const ClientEdit = () => {

    const { id } = useParams();
    let navigate = useNavigate();

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(JSON.stringify({ name: name, email: email }));
        await fetch("/api/clients" + (id === 'new' ? "" : "/" + id), {
            method: (id === 'new') ? "POST" : "PUT",
            body: JSON.stringify({ name: name, email: email }),
            headers: {
                "Accept": "applicatiom.json",
                "Content-Type": "application/json"
            }

        });
        navigate("/clients")
    }

    useEffect(() => {
        fetch(`/api/clients/${id}`)
            .then(response => response.json())
            .then(data => {
                setName(data.name);
                setEmail(data.email);
            })
    }, [id])

    const title = <h2>{id === 'new' ? "Add Client" : "Edit Client"}</h2>

    return (
        <div>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name"
                            value={name} autoComplete="name" onChange={(e) => {
                                setName(e.target.value);
                            }} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email"
                            value={email} autoComplete="email" onChange={(e) => {
                                setEmail(e.target.value);
                            }} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/clients">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    );
}

export default ClientEdit;