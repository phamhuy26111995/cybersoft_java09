import React from "react";
import Theme from "./Theme";
import {styled} from 'styled-components'


const StyledDiv = styled.div`
    color : ${props => props.theme.textColor};
    background-color : ${props => props.theme.backGroundColor}
`

function ComponentOne() {


    return (
        <Theme>
            <StyledDiv>
                Hello world
            </StyledDiv>
        </Theme>
    )
}

export default ComponentOne;