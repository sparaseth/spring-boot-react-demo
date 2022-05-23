import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Navbar, NavbarBrand } from "reactstrap";

class AppNavBar extends Component {

    constructor(props) {
        super(props);
        this.state = { isOpen: false };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <Navbar color="dark" dark expand="md">
                <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            </Navbar>
        )
    }
}

export default AppNavBar;