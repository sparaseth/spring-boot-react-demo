import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Button, Container } from "reactstrap";

class Home extends Component {

    render() {
        return (
            <div>
                <Container fluid>
                    <Button color="link"><Link to="clients">Clients</Link></Button>
                    <Button color="link"><Link to="students">Students</Link></Button>
                </Container>
            </div>
        )
    }
}

export default Home;