! Author: Lucas Marques Moreno
! Date: 08/2020
! Description: A program that
! can read a aifoil(.dat) file
! and aplly a rotation matrix

program airfoilPosition

    implicit none
    
    real,dimension(200,2) :: airfoil, rotateAirfoil, airfoilUpper, airfoilLower !variable to store .dat file points (x,y) coordenates
    real :: twistAngle = 0, radAngle = 0
    real :: chord = 0 
    real :: span = 0 ! distance between the root and the plane to be placed
    real :: offset = 0, shift = 0, maxThickness =0, temp = 0
    integer :: alignment !variable that store if the new airfoil must be align by 1/4 chord length
    CHARACTER(LEN=200) :: airfoilName
    CHARACTER(LEN=250) :: saveName
    integer :: i, j, col, changePoint, maxThicknessPosition, difPoints
    integer :: nlinesDat  ! number of lines in the dat file
    REAL, PARAMETER :: Pi = 3.1415927
    
    
    print *, "========================================"
    print *
    print *, "Welcome to AirFoil Postion"
    print *, "        ______         ________" 
    print *, "       /      \        |   ____|"
    print *, "      /   __   \       |  |__"
    print *, "     /   /  \   \      |  |__|"
    print *, "    /   /    \   \     |  |"
    print *, "   /   /______\   \    |  |"
    print *, "  /   __________   \   | /"
    print *, " /___/          \___\  |/"

    print *
    print *, "========================================"


!================= Input Block ==============================

    print *, "Enter the airfoil file name (with extension): "
    read(*,*) airfoilName

    print *, "How many lines does it have ?"
    read(*,*) nlinesDat

    print *, "Enter the airfoil chord: "
    read(*,*) chord

    print *, "Enter the twist angle: "
    read(*,*) twistAngle

    print *, "Enter the span distance: "
    read(*,*) span

    print *, "Align by:"   
    print *," > 1/4 chord[1]"
    print *," > Offset[2]"
    print *," > Maximum Thickness[3]"
    print *," > None[4]"

    read(*,*) alignment

    if(alignment == 2) then
        print *, "Enter the offset: "
        read(*,*) offset
    endif
    

!============================================================

!================ .Dat Manipulation Block ===================
    
    print*, "READING FILE..."

    open(10, file=airfoilName)
    
    DO i = 1,nlinesDat
        READ(10,*) (airfoil(i,col),col=1,2)
    end do
    
    close(10)
    
    print*, "CALCULATING..."

    if(alignment == 1) then
        shift = chord *0.25
    endif
    if(alignment == 2) then
        shift = offset
    endif
    if(alignment == 3) then
        !Calc Max thickness
        do i =2,nlinesDat
            if(airfoil(i,1) < airfoil(i-1,1)) then
                changePoint = i
            end if
            exit
        end do

        do i=1,changePoint
            airfoilUpper(i,1) = airfoil(i,1)
            airfoilUpper(i,2) = airfoil(i,2)
        end do
        
        difPoints = nlinesDat-changePoint

        do j=1,difPoints
            do i=nlinesDat,changePoint,-1
                airfoilLower(j,1) = airfoil(i,1)
                airfoilLower(j,2) = airfoil(i,2)
            end do
        end do

        do i=1,SIZE(airfoilUpper)
            temp = abs(airfoilUpper(i,2)-airfoilLower(i,2))
            if(temp > maxThickness) then
                maxThickness = temp
                maxThicknessPosition = i
            end if
        end do

        shift = airfoil(maxThicknessPosition,1)*chord
    endif    
    if(alignment == 4) then
        shift = 0
    endif

    radAngle = twistAngle*Pi/180



    do i =1,nlinesDat
        rotateAirfoil(i,1) = (airfoil(i,1)*chord-shift)*cos(radAngle) + airfoil(i,2)*sin(radAngle)*chord
        rotateAirfoil(i,2) = (airfoil(i,2)*cos(radAngle) - airfoil(i,1)*sin(radAngle)) * chord
    end do
    
    
    saveName = "rotate_"//airfoilName

    open(20 ,file=saveName)
    
    DO i=1,nlinesDat
        write(20,*) rotateAirfoil(i,1), rotateAirfoil(i,2), span
    end do
    
    close(20)


    print*, "THE PROCESS COMPLETED SUCCESSFULLY"
    print*, saveName, "HAS BEEN CREATED"

!============================================================


end program airfoilPosition
