import * as React from "react"
import Box from "@mui/material/Box"
import { Checkbox, TextField } from "@mui/material"
import { createTheme, ThemeProvider } from "@mui/material/styles"
import { makeStyles } from "@mui/styles"
import AccountCircleIcon from "@mui/icons-material/AccountCircle"
import TopTenPoll from "./TopTenPoll"

const label = { inputProps: { "aria-label": "Checkbox demo" } }
/*
const newPageStyles = makeStyles((theme) => ({
  outerContainer: {
    "&:-webkit-scrollbar": {
      width: "5px",
    },
    "&:-webkit-scrollbar-track": {
      background: "#f1f1f1",
    },
    "&:-webkit-scrollbar-thumb": {
      background: "#888",
    },
    "&:-webkit-scrollbar-thumb:hover": {
      background: "#555",
    },
    "-ms-overflow-style": "none",
  },
}));
*/

export default function TopTen(props) {
    // const classes = newPageStyles();
    const PollsMap = () => {
        const data = props.data
        return data.map(el => <TopTenPoll data={el} />)
    }
    return (
        <Box sx={{ mt: 0.2, mb: 3 }}>
            <Box className="Title" sx={{ pl: 0.3, mt: 0.3 }}>
                🏆 Top 10
            </Box>
            <Box
                className="outerContainer"
                sx={{
                    display: "flex",
                    height: "150px",
                    width: "100%",
                    mt: 0.8,
                    letterSpacing: 1.2,
                    borderColor: "grey.500",
                    flexDirection: "row",
                    overflow: "auto",
                    "&::-webkit-scrollbar": {
                        display: "none",
                    },
                    "-ms-overflow-style": "none",
                    //overflow: "hidden",
                }}
            >
                {PollsMap()}
            </Box>
        </Box>
    )
}