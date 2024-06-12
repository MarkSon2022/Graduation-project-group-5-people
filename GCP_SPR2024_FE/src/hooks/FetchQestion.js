import { useEffect, useState } from "react";
import data from "../data/question";
import { useDispatch } from "react-redux";

export const useFetchQestion = () => {
    const dispath = useDispatch();
    const [getData, setGetData] = useState({ isLoading : false, apiData : [], serverError: null});

    useEffect(() => {
        setGetData(prev => ({...prev, isLoading : true}));

        (async () => {
            try {
                let question = await data

                if(question.length > 0){
                    setGetData(prev => ({...prev, isLoading : false}));
                    setGetData(prev => ({...prev, apiData : question}));

                    /** dispatch an action */
                    dispath()

                }
            } catch (error) {
                setGetData(prev => ({...prev, isLoading : false}));
                setGetData(prev => ({...prev, serverError : error}));
            }
        })();
    })
}