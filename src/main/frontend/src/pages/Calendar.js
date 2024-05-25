import React, { Component, useEffect, useState } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { useSelector } from 'react-redux';
import { AddPlanModal } from '../components/AddPlanModal';
import { isDateBetween } from '../function/isDateBetween';

export default function Calendar() {
    const plans = useSelector((state)=>state.user.plans)
    const [dayPlans, setDayPlans] = useState();
    const [showModal, setShowModal] = useState(false);
    const [date, setDate] = useState();

    const handleDateClick = (arg) => {
        setDate(arg.dateStr)
        setDayPlans(plans.filter((plan) => isDateBetween(plan.start, plan.end, arg.dateStr)));
        setShowModal(true)
    };

    useEffect(() => {
        console.log(dayPlans);
    }, [dayPlans]);



    return (
        <div>
            <div id="calendar">
                <FullCalendar
                    eventTextColor="#474747"
                    dateClick={handleDateClick}
                    plugins={[dayGridPlugin, interactionPlugin]}
                    initialView="dayGridMonth"
                    events={
                        plans
                    }
                />
            
            </div>
            {showModal ? <AddPlanModal date={date} plans={dayPlans} setShowModal={setShowModal} /> : null}

        </div>
    );
}
